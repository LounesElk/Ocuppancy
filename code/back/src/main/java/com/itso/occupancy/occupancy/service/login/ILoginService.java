package com.itso.occupancy.occupancy.service.login;

import com.itso.occupancy.occupancy.dto.model.login.JwtAuthInputDto;

public interface ILoginService {

    String Login(JwtAuthInputDto authenticationRequest, boolean shortToken);
}
