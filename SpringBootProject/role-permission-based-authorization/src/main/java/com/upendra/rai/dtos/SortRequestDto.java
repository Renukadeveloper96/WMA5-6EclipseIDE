package com.upendra.rai.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SortRequestDto {

  @NotBlank(message = "NotBlank.sortRequestDto.fromId")
  private String fromId;
  @NotBlank(message = "NotBlank.sortRequestDto.toId")
  private String toId;

}
