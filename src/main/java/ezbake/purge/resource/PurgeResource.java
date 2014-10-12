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


import ezbake.purge.api.WarehouseEntryApi;
import ezbake.services.provenance.thrift.PurgeInitiationResult;
import ezbake.purge.domain.purge.PurgeState;
import ezbake.purge.domain.purge.PurgeSubmit;
import ezbake.purge.domain.purge.PurgeSubmitResult;
import ezbake.purge.domain.purge.ResolvePurgeSubmit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * <p>
 * This resource is responsible for providing RESTful interfacing to the Central
 * Purge service.
 * </p>
 */
@Path("/purge")
public class PurgeResource {
   
   private static Logger logger = LoggerFactory.getLogger(PurgeResource.class);
  
   /**
    * <p>
    * Initiates a purge event to delete the entries that are identified by the
    * list of URIs. The name and description describe the purge event for future
    * reference.
    * </p>
    * 
    * @param   purge A object detailing the purge being started
    * @return  A Response object that indicates success or failure.
    */
   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   public Response purge(PurgeSubmit purge) {
      
      PurgeInitiationResult serviceResult = null;
      PurgeSubmitResult result = new PurgeSubmitResult();
      
      try {
         serviceResult = new WarehouseEntryApi().removeEntries(purge.getName(), purge.getDescription(), purge.getUris());
      } catch (Exception e) {
         logger.error("An error occurred when submitting a purge request.", e);
         return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
      }
      
      result.setPurgeId(serviceResult.getPurgeId());
      result.setUrisNotFound(serviceResult.getUrisNotFound());
      result.setDescription(purge.getDescription());
      result.setName(purge.getName());
      
      return Response.ok(result).build();
   }
   
   /**
    * <p>
    * Returns the current purge state for the purge associated with the given
    * purgeId. If no corresponding purge state record can be found then null is
    * returned.
    * </p>
    * 
    * @param   purgeId The id of the purge. If no value is given then null is
    *          returned.
    * @return  A Response object that contains the CentralPurgeState object
    *          describing the state if successful.
    */
   @GET
   @Path("/status/{purgeId}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response purgeStatus(@PathParam("purgeId") Long purgeId) {
      
      PurgeState state = null;
      
      try {
         state = new WarehouseEntryApi().getPurgeState(purgeId);
      } catch (Exception e) {
         logger.error("An error occurred while fetching the purge state for purge id " + purgeId, e);
         return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
      }
      
      if (state == null) {
         return Response.status(Response.Status.NOT_FOUND).build();
      }
      
      return Response.ok(state).build();
   }

    /**
     * <p>
     * Returns all the purges currently requiring manual intervention
     * </p>
     *
     * @return  A Response object that contains a list of all the purges
     *          requiring manual intervention if successful.
     */
    @GET
    @Path("/manual-intervention")
    @Produces(MediaType.APPLICATION_JSON)
    public Response purgesManualIntervention() {
        List<PurgeState> purgeIds;

        try {
            purgeIds = new WarehouseEntryApi().getPurgesRequiringManualIntervention();
        } catch (Exception e) {
            logger.error("An error occurred while fetching the purges requiring manual intervention ", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }

        if (purgeIds == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(purgeIds).build();
    }
    /**
     * <p>
     * Returns all the purges currently requiring manual intervention
     * </p>
     *
     * @return  A Response object that contains a list of all the purges
     *          requiring manual intervention if successful.
     */
    @POST
    @Path("/resolvePurge")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response resolvePurge(ResolvePurgeSubmit purgeSubmit) {

        try {
            new WarehouseEntryApi().resolvePurge(purgeSubmit.getPurgeId(), purgeSubmit.getResolveNote());
        } catch (Exception e) {
            logger.error("An error occurred while fetching the purges requiring manual intervention ", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }

        return Response.ok().build();
    }
}