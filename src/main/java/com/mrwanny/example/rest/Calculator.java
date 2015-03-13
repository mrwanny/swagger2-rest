package com.mrwanny.example.rest;

import com.mrwanny.example.model.Result;
//import com.wordnik.swagger.model.*;
import com.wordnik.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.http.MediaType.*;

@Controller
@RequestMapping( value = "/calculator", produces = {APPLICATION_JSON_VALUE})
@Api(value = "/calculator", description = "Arithmetic operations")
class Calculator{
	@ApiOperation(value = "adds 2 numbers", notes  = "return",response = String.class)
	@ApiResponses(value =
			{@ApiResponse(code = 200, message = "sum", response = Result.class)}
	)
	@RequestMapping( value = "/add", method = RequestMethod.GET )
	@ResponseBody
	public Result add(@ApiParam(value = "a", required = true, defaultValue = "0") @RequestParam("a")Double a, @ApiParam(value = "b", required = true, defaultValue = "0") @RequestParam("b" )Double b) {
		Result res = new Result();
		res.setResult(a+b);
		return res;
	}
}
