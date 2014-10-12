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

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ezbake.purge.api.WarehouseEntryApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ezbake.purge.domain.document.EntryDetail;

/**
 * <p>
 * This resource is responsible for providing a RESTful interface to obtaining
 * purge entries.
 * </p>
 */
@Path("/entries")
public class EntryResource {
   
   private static Logger logger = LoggerFactory.getLogger(EntryResource.class);

   /**
    * <p>
    * Fetch the purge entry identified by the given URI.
    * </p>
    * 
    * @param   uri The URI of the purge entry to fetch. Required.
    * @return  The purge entry that is identified by the given uri or null if
    *          no corresponding entry was found.
    * @return  A response object that indicates success or failure.
    */
   @GET
   @Path("/{uri}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response fetch(@PathParam("uri") String uri) {

      EntryDetail entry = null;
      
      try {
         uri = URLDecoder.decode(uri, "UTF-8");
      } catch (UnsupportedEncodingException e) {
         logger.error("An error occured while decoding the URI upon fetching an entry: " + uri, e);
         return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
      }

      try {
         entry = new WarehouseEntryApi().fetchEntry(uri);
      } catch (Exception e) {
         logger.error("An error occurred while fetching the URI from the purge: " + uri, e);
         return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
      }
      
      if (entry == null) {
         return Response.status(Response.Status.NOT_FOUND).entity("The purge document " + uri + " was not found.").build();
      }
      return Response.ok(entry).build();
   }
}
