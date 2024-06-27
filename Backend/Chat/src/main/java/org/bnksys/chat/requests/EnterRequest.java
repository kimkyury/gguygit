package org.bnksys.chat.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EnterRequest {

    private Long userId;
    private Long chatroomId;

}
