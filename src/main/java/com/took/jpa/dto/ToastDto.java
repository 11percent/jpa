package com.took.jpa.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ToastDto {
    private String title;
    private String content;
    private boolean isShow;
}
