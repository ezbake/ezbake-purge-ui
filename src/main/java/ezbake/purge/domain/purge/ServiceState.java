package ezbake.purge.domain.purge;

import java.io.Serializable;
import java.util.Date;

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
 * The purge state for an application's service.
 * </p> 
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceState implements Serializable {

   private static final long serialVersionUID = 1L;
   
   private String serviceName;
   private String status;
   private Date initiatedTimestamp;
   private Date lastPollTimestamp;
   
   public ServiceState() {
   }
   
   public String getServiceName() {
      return serviceName;
   }
   
   public void setServiceName(String serviceName) {
      this.serviceName = serviceName;
   }
   
   public String getStatus() {
      return status;
   }
   
   public void setStatus(String status) {
      this.status = status;
   }
   
   public Date getInitiatedTimestamp() {
      return initiatedTimestamp;
   }
   
   public void setInitiatedTimestamp(Date initiatedTimestamp) {
      this.initiatedTimestamp = initiatedTimestamp;
   }
   
   public Date getLastPollTimestamp() {
      return lastPollTimestamp;
   }
   
   public void setLastPollTimestamp(Date lastPollTimestamp) {
      this.lastPollTimestamp = lastPollTimestamp;
   }

}
