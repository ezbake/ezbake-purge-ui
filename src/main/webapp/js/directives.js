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

/**
 * @description
 * A component that executes logic as provided by the implementor whenever
 * the enter key up action occurs on the element.
 * 
 * @usage
 * <input wh-enterup="doSomething()">
 */
wh.app.directive('whEnterup', function() {
      return function(scope, element, attrs) {
         element.bind('keyup', function(event) {
            if (event.which == '13') {
               scope.$apply(attrs.whEnterup);
            }
         });
      };
   }
);

/**
 * @description
 * A component that executes logic as provided by the implementor whenever
 * the up action occurs on the given keys.
 * 
 * @usage
 * <input wh-keyup="doSomething()" keys="[27,13]">
 */
wh.app.directive('whKeyup', function() {
      return function(scope, element, attrs) {
         function applyKeyup() {
            scope.$apply(attrs.whKeyup);
         };
         
         var allowedKeys = scope.$eval(attrs.keys);
         element.bind('keyup', function(event) {
            if (!allowedKeys || allowedKeys.length == 0) {
                applyKeyup();
            } else {
                angular.forEach(allowedKeys, function(key) {
                    if (key == event.which) {
                        applyKeyup();
                    }
                });
            }
         });
      };
   }
);
