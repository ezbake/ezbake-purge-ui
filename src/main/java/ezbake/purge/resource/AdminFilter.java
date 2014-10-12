/*   Copyright (C) 2013-2014 Computer Sciences Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. */

package ezbake.purge.resource;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import ezbake.configuration.EzConfiguration;
import ezbake.configuration.EzConfigurationLoaderException;
import ezbake.security.client.EzbakeSecurityClient;
import ezbake.thrift.ThriftClientPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * <p>
 * Filters HTTP requests to confirm that the user has the proper rights access
 * to this service.
 * </p>
 */
public class AdminFilter implements ContainerRequestFilter {
   
   private static EzConfiguration config;
   private static ThriftClientPool pool;
   private static EzbakeSecurityClient security;
   private static Logger logger = LoggerFactory.getLogger(AdminFilter.class);

   static {
       try {
          config = new EzConfiguration();
       } catch (EzConfigurationLoaderException e) {
          logger.error("The properties could not be loaded.", e);
          throw new RuntimeException(e);
       }
       pool = new ThriftClientPool(config.getProperties());
       security = new EzbakeSecurityClient(config.getProperties());
  }

   @Override
   public ContainerRequest filter(ContainerRequest request) {
       try {
           EzSecurityTokenWrapper securityToken = security.fetchTokenForProxiedUser();
           Set<Long> userGroups = securityToken.getAuthorizations().getPlatformObjectAuthorizations();
           
           EzBakeApplicationConfigurationHelper configHelper = new EzBakeApplicationConfigurationHelper(config.getProperties()); 
           String groupName = EzGroupNamesConstants.APP_GROUP + EzGroupNamesConstants.GROUP_NAME_SEP + configHelper.getApplicationName();
           
           Set<Long> groupsMask = null;
           EzGroups.Client groupClient = null;
           
           try {
              groupClient = pool.getClient(EzGroupsConstants.SERVICE_NAME, EzGroups.Client.class);
              EzSecurityTokenWrapper groupsSecurityToken = security.fetchDerivedTokenForApp(securityToken, pool.getSecurityId(EzGroupsConstants.SERVICE_NAME));
              groupsMask = groupClient.getGroupsMask(groupsSecurityToken, Sets.newHashSet(groupName));
           } finally {
              pool.returnToPool(groupClient);
           }
           
           if (Sets.intersection(userGroups, groupsMask).isEmpty()) {
              logger.warn("DN {} does not have access rights to purge.", securityToken.getUserId());
              throw new WebApplicationException(Response.status(Response.Status.FORBIDDEN).entity("The user does not have access rights to Purge.").build());
           }
           
           return request;
       } catch (TException e) {
           logger.error("Unable to obtain a token from security service.", e);
           throw new WebApplicationException(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Unable to obtain a token from security service.").build());
       }
   }
}
