package ezbake.purge.domain.purge;

import java.io.Serializable;

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
 * Identifies a document that was submitted for purging and provides its current
 * status. The purge document id is the corresponding id used by the provenance
 * and central purge services.
 * </p>
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DocumentState implements Serializable {

   private static final long serialVersionUID = 1L;
   
   private String uri;
   private Long purgeDocumentId;
   private PurgeDocumentStatus status;
   
   public DocumentState() {
   }
   
   public DocumentState(String uri, Long purgeDocumentId, PurgeDocumentStatus status) {
      
      this.uri = uri;
      this.purgeDocumentId = purgeDocumentId;
      this.status = status;
   }
   
   public String getUri() {
      return uri;
   }
   
   public void setUri(String uri) {
      this.uri = uri;
   }
   
   public Long getPurgeDocumentId() {
      return purgeDocumentId;
   }

   public void setPurgeDocumentId(Long purgeDocumentId) {
      this.purgeDocumentId = purgeDocumentId;
   }

   public PurgeDocumentStatus getStatus() {
      return status;
   }
   
   public void setStatus(PurgeDocumentStatus status) {
      this.status = status;
   }

   public enum PurgeDocumentStatus {
      
      NOT_FOUND,
      NOT_YET_PURGED,
      PURGED;
   }

}
