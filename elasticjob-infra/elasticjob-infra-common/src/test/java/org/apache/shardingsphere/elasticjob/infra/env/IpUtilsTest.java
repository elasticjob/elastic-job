/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.elasticjob.infra.env;

import java.lang.reflect.Method;
import java.net.InetAddress;
import lombok.SneakyThrows;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public final class IpUtilsTest {
    
    @Test
    public void assertGetIp() {
        assertNotNull(IpUtils.getIp());
    }
    
    @Test
    @SneakyThrows
    public void assertGetHostName() {
        assertNotNull(IpUtils.getHostName());
        Field field = IpUtils.class.getDeclaredField("cachedHostName");
        field.setAccessible(true);
        String hostName = (String) field.get(null);
        assertThat(hostName, is(IpUtils.getHostName()));
    }
    
    @Test
    public void assertIsReachable() throws Exception {
        Method method = IpUtils.class.getDeclaredMethod("isRuntimeReachable", InetAddress.class);
        method.setAccessible(true);
        InetAddress addr = InetAddress.getLocalHost();
        boolean value1 = addr.isReachable(100);
        boolean value2 = (boolean) method.invoke(null, addr);
        assertEquals(value1, value2);
        
        addr = InetAddress.getByName("www.google.com");
        value1 = addr.isReachable(100);
        value2 = (boolean) method.invoke(null, addr);
        assertNotEquals(value1, value2);
    }
}
