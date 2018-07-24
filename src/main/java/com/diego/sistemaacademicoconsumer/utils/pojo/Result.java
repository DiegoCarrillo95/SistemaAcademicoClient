package com.diego.sistemaacademicoconsumer.utils.pojo;

import lombok.Getter;
import lombok.Setter;

public class Result {

	@Getter
	@Setter
    private NameEntry name;

    public Result() {
    	
    }

    public Result(NameEntry name) {
        this.name = name;
  
    }

   
    
}