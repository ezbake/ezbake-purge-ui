package ezbake.purge.domain.purge;

import java.io.Serializable;
import java.util.ArrayList;
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

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PurgeSubmitResult implements Serializable {
   
   private static final long serialVersionUID = 1L;
   
   private long purgeId;
   private List<String> urisNotFound = new ArrayList<String>();
   private String name;
   private String description;
   
   public long getPurgeId() {
      return purgeId;
   }
   
   public void setPurgeId(long purgeId) {
      this.purgeId = purgeId;
   }
   
   public boolean addUriNotFound(String uri) {
      return this.urisNotFound.add(uri);
   }
   
   public List<String> getUrisNotFound() {
      return urisNotFound;
   }
   
   public void setUrisNotFound(List<String> urisNotFound) {
      
      if (urisNotFound == null) {
         this.urisNotFound.clear();
      } else {
         this.urisNotFound = urisNotFound;
      }
   }
   
   public String getName() {
      return name;
   }
   
   public void setName(String name) {
      this.name = name;
   }
   
   public String getDescription() {
      return description;
   }
   
   public void setDescription(String description) {
      this.description = description;
   }
   
 }
