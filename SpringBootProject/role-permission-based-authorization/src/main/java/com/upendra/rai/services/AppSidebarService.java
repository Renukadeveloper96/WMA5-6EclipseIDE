package com.upendra.rai.services;

import java.util.List;

import com.upendra.rai.dtos.AppSidebarRequestDto;
import com.upendra.rai.dtos.AppSidebarResponseDto;

public interface AppSidebarService {

public	AppSidebarResponseDto createAppSidebar(Long id,AppSidebarRequestDto appSidebarRequestDto);
//public AppSidebarResponseDto getAllAppSidebar();
public List<AppSidebarResponseDto> getAllAnnouncementTopic(Long appIconId);
public AppSidebarResponseDto updateAppSidebar(Long appSidebarId, AppSidebarRequestDto appSidebarRequestDto);
//
public  String deleteAppSidebar(Long appSidebarId);

  void activateAppSidebar(Long appSidebarId);

  void deactivateAppSidebar(Long appSidebarId);

public AppSidebarResponseDto getAppSidebarById(Long appIconId ,Long appSidebarId);

public List<AppSidebarResponseDto> getAllAppSidebar();

//  void sort(SortRequestDto sortDto);

}
