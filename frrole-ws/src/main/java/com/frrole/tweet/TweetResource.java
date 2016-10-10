package com.frrole.tweet;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.frrole.SearchCriterion;

@Path("tweet")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TweetResource {

	@Inject
	TweetImpl impl;

	@GET
	public Response get() {
		String helpMessage = "Pass paramter to POST endpoints to get results";
		return Response.ok(helpMessage).build();
	}

	@POST
	public Response search(SearchCriterion criterion) {
		try {
			String result = impl.search(criterion);
			return Response.ok(result)
					.header("Content-Disposition", "attachment; filename=tweets." + criterion.getFormat()).build();
		} catch (IOException e) {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

	@POST
	@Path("/count")
	public Response count(SearchCriterion criterion) {
		try {
			int result = impl.count(criterion);
			return Response.ok(result).build();
		} catch (IOException e) {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}

}
