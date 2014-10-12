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

wh.app.factory('SpinnerService', 
   [ function() {
      
      return {
         isInProgress : false,
         title : "",
         message : "",
         on : function(message, title) {
            this.message = message;
            this.title = title;
            this.isInProgress = true;
         },
         off : function() {
            this.isInProgress = false;
            this.title = "";
            this.message = "";
         }
      };
   }]
);
      
wh.app.factory('ErrorService', 
   [ function() {
      
      return {
         title : "",
         message : "",
         getMessage : function() {
            return this.message;
         },
         setMessage : function(message) {
            this.message = message;
         },
         getTitle : function() {
            return this.title;
         },
         setTitle : function(title) {
            this.title = title;
         },
         reset : function() {
            this.title = "";
            this.message = "";
         }
      };
      
   }]
);

wh.app.factory('EntryService',
   ['$http', function($http) {
      
      var resourceUrl = 'api/entries';
      
      return {
         entry : null,
         setEntry : function(entry) {
            this.entry = entry;
         },
         getEntry : function() {
            return this.entry;
         },
         hasEntry : function(uri) {
            return uri && this.entry && uri == this.entry.uri;
         },
         reset : function() {
            this.entry = null;
         },
         encodeWarehouseUri : function(uri) {
            return encodeURIComponent(encodeURIComponent(uri));
         },
         
         /**
          * @description
          * Fetches the warehouse entry that is identified by the given uri.
          *  
          * @param   uri {String} The uri that identifies the warehouse entry.
          *          Required.
          * @returns The promise object of the http ajax call. This allows the
          *          caller to chain onsuccess and onerror functions. 
          */
         get : function(uri) {
            return $http.get(resourceUrl + "/" + this.encodeWarehouseUri(uri));
         }         
      };
   }]
);

wh.app.factory('PurgeService',
   ['$http', function($http) {
   
      var resourceUrl = 'api/purge';
      
      return {
         
         purgeResult : {},
         
         /**
          * @description
          * Generate and return a UUID.
          * 
          * @returns A universally unique identifier string.
          */
         generateGuid : function ()
         {
             return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
                 var r = Math.random()*16|0, v = c === 'x' ? r : (r&0x3|0x8);
                 return v.toString(16);
             });
         },
         
         /**
          * @description
          * Sets the result that is returned upon a purge initiation.
          * 
          * @param   result {wh.model.purgeResult} 
          */
         setPurgeInitiationResult : function(result) {
            this.purgeResult = result;
         },
         
         /**
          * @description
          * Returns the purge initiation result.
          */
         getPurgeInitiationResult : function() {
            return this.purgeResult;
         },
         
         /**
          * @description
          * Deletes the list of warehouse entries identified by the array of
          * URIs.
          * 
          * @param   purgeObject {Object} An object that consists of the
          *          following elements:
          *          name {String} - The unique name of the purge event. Required.
          *          description {String} - A description of the purpose of the
          *          purge event. Required.
          *          uris {Array<String>} - A list of URIs to be purged. Required.
          * @returns The promise object of the http ajax call. This allows the
          *          caller to chain onsuccess and onerror functions.
          */
         purge : function(purgeObject) {
            return $http.post(resourceUrl, purgeObject);
         },
         
         /**
          * @description
          * Gets the purge status associated with the request identified by the
          * given purgeId.
          * 
          * @param   purgeId {String} The unique identifier of the purge
          *          request. Required.
          * @returns The promix object of the http ajax call. The data will be
          *          a CentralPurgeState object.
          */
         getStatus : function(purgeId) {
             return $http.get(resourceUrl + "/status/" + purgeId, {
                 headers: {
                     "Content-Type": "application/json"
                 }
             });
         },

         getPurgesManualIntervention : function() {
              return $http.get(resourceUrl + "/manual-intervention",{
                 headers: {
                     "Content-Type": "application/json"
                 }
             });
         },
         resolvePurge : function(resolvePurgeSubmit){
             return $http.post(resourceUrl+"/resolvePurge", resolvePurgeSubmit);
         }


      };
   }]
);

