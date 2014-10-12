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

package ezbake.purge.domain.document;

import java.io.Serializable;
import java.util.Date;


public class EntryVersion implements Serializable {

   private static final long serialVersionUID = 1L;
   
   private String uri;
   private Date timestamp;
   private String classification;
   private String securityId;
   
   public EntryVersion() {
      
   }
   
   public EntryVersion(String uri, Date timestamp, String classification, String securityId) {
      
      this.setUri(uri);
      this.setTimestamp(timestamp);
      this.setClassification(classification);
      this.setSecurityId(securityId);
   }
   
   public String getUri() {
      return uri;
   }
   
   public void setUri(String uri) {
      this.uri = uri;
   }
   
   public Date getTimestamp() {
      return timestamp;
   }
   
   public void setTimestamp(Date timestamp) {
      this.timestamp = timestamp;
   }
   
   public String getClassification() {
      return classification;
   }
   
   public void setClassification(String classification) {
      this.classification = classification;
   }
   
   public String getSecurityId() {
      return securityId;
   }
   
   public void setSecurityId(String securityId) {
      this.securityId = securityId;
   }

}
