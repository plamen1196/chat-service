package com.chat.service.chat_service.config;

import org.mapstruct.ReportingPolicy;

@org.mapstruct.MapperConfig(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MapperConfig {
}
