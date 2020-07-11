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

package org.apache.shardingsphere.elasticjob.lite.executor.item;

import org.apache.shardingsphere.elasticjob.infra.common.exception.JobConfigurationException;
import org.apache.shardingsphere.elasticjob.lite.fixture.executor.ClassedFooJobExecutor;
import org.apache.shardingsphere.elasticjob.lite.fixture.executor.TypedFooJobExecutor;
import org.apache.shardingsphere.elasticjob.lite.fixture.job.DetailedFooJob;
import org.apache.shardingsphere.elasticjob.lite.fixture.job.FailedJob;
import org.apache.shardingsphere.elasticjob.lite.fixture.job.FooJob;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;

public final class JobItemExecutorFactoryTest {
    
    @Test(expected = JobConfigurationException.class)
    public void assertGetExecutorByClassFailureWithInvalidType() {
        JobItemExecutorFactory.getExecutor(FailedJob.class);
    }
    
    @Test
    public void assertGetExecutorByClassSuccessWithCurrentClass() {
        assertThat(JobItemExecutorFactory.getExecutor(FooJob.class), instanceOf(ClassedFooJobExecutor.class));
    }
    
    @Test
    public void assertGetExecutorByClassSuccessWithSubClass() {
        assertThat(JobItemExecutorFactory.getExecutor(DetailedFooJob.class), instanceOf(ClassedFooJobExecutor.class));
    }
    
    @Test(expected = JobConfigurationException.class)
    public void assertGetExecutorByTypeFailureWithInvalidType() {
        JobItemExecutorFactory.getExecutor("FAIL");
    }
    
    @Test
    public void assertGetExecutorByTypeSuccess() {
        assertThat(JobItemExecutorFactory.getExecutor("FOO"), instanceOf(TypedFooJobExecutor.class));
    }
}
