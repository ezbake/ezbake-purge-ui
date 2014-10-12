package ezbake.purge.domain.purge;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
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

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p>
 * The collection of applications that are registered with the central purge
 * service and provides the purge state for each service.
 * </p>
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ApplicationState implements Serializable {

   private static final long serialVersionUID = 1L;
   
   private String applicationName;
   private List<ServiceState> serviceStates;
   
   public String getApplicationName() {
      return applicationName;
   }
   
   public void setApplicationName(String applicationName) {
      this.applicationName = applicationName;
   }
   
   public List<ServiceState> getServiceStates() {
      return serviceStates;
   }
   
   public void setServiceStates(List<ServiceState> serviceStates) {
      this.serviceStates = serviceStates;
   }
   
   public void addServiceState(ServiceState serviceState) {
      
      if (this.serviceStates == null) {
         this.serviceStates = new ArrayList<ServiceState>();
      }
      this.serviceStates.add(serviceState);
   }
   
   public void addServiceState(String serviceName, String status, Date initiatedTimestamp, Date lastPollTimestamp){
      
      ServiceState ss = new ServiceState();
      ss.setInitiatedTimestamp(initiatedTimestamp);
      ss.setLastPollTimestamp(lastPollTimestamp);
      ss.setServiceName(serviceName);
      ss.setStatus(status);
      this.addServiceState(ss);
   }

}
