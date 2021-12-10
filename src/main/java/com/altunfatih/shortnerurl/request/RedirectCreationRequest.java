package com.altunfatih.shortnerurl.request;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RedirectCreationRequest {
    @NotNull
    private String alias;
    @NotNull
    private String url;
}