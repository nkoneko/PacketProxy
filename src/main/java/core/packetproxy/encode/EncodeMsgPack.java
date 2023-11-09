/*
 * Copyright 2019,2023 DeNA Co., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package packetproxy.encode;

import packetproxy.common.MessagePack;
import packetproxy.http.Http;

public class EncodeMsgPack extends EncodeHTTPBase {

	public EncodeMsgPack(String ALPN) throws Exception {
		super(ALPN);
	}

    @Override
    public String getName() {
        return "MessagePack over HTTP";
    }

    @Override
    protected Http decodeClientRequestHttp(Http inputHttp) throws Exception {
        inputHttp.setBody(MessagePack.decode(inputHttp.getBody()).getBytes());
        return inputHttp;
    }

    @Override
    protected Http encodeClientRequestHttp(Http inputHttp) throws Exception {
        inputHttp.setBody(MessagePack.encode(new String(inputHttp.getBody())));
        return inputHttp;
    }

    @Override
    protected Http decodeServerResponseHttp(Http inputHttp) throws Exception {
        inputHttp.setBody(MessagePack.decode(inputHttp.getBody()).getBytes());
        return inputHttp;
    }

    @Override
    protected Http encodeServerResponseHttp(Http inputHttp) throws Exception {
        inputHttp.setBody(MessagePack.encode(new String(inputHttp.getBody())));
        return inputHttp;
    }

}