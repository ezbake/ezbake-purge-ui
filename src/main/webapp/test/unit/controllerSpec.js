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

describe('Purge UI controllers', function() {
   beforeEach(module('purgeUI'));
     
   describe('WarehouseListController', function(){
     
      it('should create "feed" model with 4 feeds', inject(function($controller) {
         var scope = {},
         ctrl = $controller('WarehouseListController', { $scope: scope });
     
         expect(scope.feeds.length).toBe(4);
      }));
   });
});
