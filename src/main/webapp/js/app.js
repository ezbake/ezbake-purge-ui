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

var wh = wh || {};

wh.app = angular.module("whApp",
    [
      'ui.router', 
      'ui.bootstrap', 
      'toaster',
      'ngMockE2E'
    ]
  );

/**
 * <p>Returns true if the given obj parameter is neither undefined
 * nor null. Returns false if the given obj parameter is undefined
 * or null.</p>
 * 
 * @param   obj {Object} An object or element.
 * @return  true if obj has a value and false if obj is undefined
 *          or null.
 */
wh.isNone = function(obj) {

   return (obj===undefined || obj===null); 
};

/**
 * <p>Returns true if obj is of type string and it has an empty
 * string value. In addition, returns true if obj is undefined or
 * null. Returns false in all other cases.
 * 
 * @param   obj An object or element.
 * @return  true if obj is an empty string or if obj is undefined or
 *          null; false otherwise.
 */
wh.isEmpty = function(obj) {

   if (wh.isNone(obj)) return true;

   if (typeof obj == "string") {
      return $.trim(obj).length == 0;
   }

   return false;
};
