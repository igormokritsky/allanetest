package com.igormokritsky.allanetest.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private T data;
    private Object errors;

    public void addErrorMsgToResponse(String msgError) {
        ResponseError error = new ResponseError()
            .setDetails(msgError)
            .setTimestamp(LocalDateTime.now());
        setErrors(error);
    }
}
