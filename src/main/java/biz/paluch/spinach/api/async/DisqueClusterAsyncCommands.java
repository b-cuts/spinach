/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package biz.paluch.spinach.api.async;

import com.lambdaworks.redis.RedisFuture;

/**
 * Asynchronous executed commands related with Disque Cluster.
 * 
 * @param <K> Key type.
 * @param <V> Value type.
 * @author Mark Paluch
 */
public interface DisqueClusterAsyncCommands<K, V> {

    /**
     * Blacklist and remove the cluster node from the cluster.
     *
     * @param nodeId the node Id
     * @return String simple-string-reply
     */
    RedisFuture<String> clusterForget(String nodeId);

    /**
     * Get information and statistics about the cluster viewed by the current node.
     *
     * @return String bulk-string-reply as a collection of text lines.
     */
    RedisFuture<String> clusterInfo();

    /**
     * Retrieve cluster leaving state.
     *
     * @return String simple-string-reply
     */
    RedisFuture<String> clusterLeaving();

    /**
     * Enable/disable cluster leaving state for a graceful cluster leave.
     *
     * @param state {@literal true} to set the leaving state, {@literal false} to un-set the leaving state
     * @return String simple-string-reply
     */
    RedisFuture<String> clusterLeaving(boolean state);

    /**
     * Meet another cluster node to include the node into the cluster. The command starts the cluster handshake and returns with
     * {@literal OK} when the node was added to the cluster.
     *
     * @param ip IP address of the host
     * @param port port number.
     * @return String simple-string-reply
     */
    RedisFuture<String> clusterMeet(String ip, int port);

    /**
     * Obtain the nodeId for the currently connected node.
     *
     * @return String simple-string-reply
     */
    RedisFuture<String> clusterMyId();

    /**
     * Obtain details about all cluster nodes. Can be parsed using {@link biz.paluch.spinach.cluster.ClusterNodesParser#parse}
     *
     * @return String bulk-string-reply as a collection of text lines
     */
    RedisFuture<String> clusterNodes();

    /**
     * Reset a node performing a soft or hard reset:
     * <ul>
     * <li>All other nodes are forgotten</li>
     * <li>All the assigned / open slots are released</li>
     * <li>If the node is a slave, it turns into a master</li>
     * <li>Only for hard reset: a new Node ID is generated</li>
     * <li>Only for hard reset: currentEpoch and configEpoch are set to 0</li>
     * <li>The new configuration is saved and the cluster state updated</li>
     * <li>If the node was a slave, the whole data set is flushed away</li>
     * </ul>
     *
     * @param hard {@literal true} for hard reset. Generates a new nodeId and currentEpoch/configEpoch are set to 0
     * @return String simple-string-reply
     */
    RedisFuture<String> clusterReset(boolean hard);

    /**
     * Save the cluster config.
     *
     * @return String simple-string-reply
     */
    RedisFuture<String> clusterSaveconfig();

}
