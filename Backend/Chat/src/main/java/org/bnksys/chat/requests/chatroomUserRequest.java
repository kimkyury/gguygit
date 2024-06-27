package org.bnksys.chat.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class chatroomUserRequest {

    private Long userId;
    private Long chatroomId;

}
