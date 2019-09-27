package com.pengpj.code.shell.elasticsearch.index

import java.util.*
import java.util.concurrent.TimeUnit

class IndexCommands {

    fun createIndex(){
//            instance.add(Calendar.DAY_OF_MONTH, 1)
//            val todayIndexPrefix = esConfig.getPreIdx() + format.format(instance.getTime())
//            for (j in 0 until hours / span) {
//                //真实索引后缀
//                val indexSuffix = String.format(indexSuffixFormat,
//                        decimalFormat.format((j * span).toLong()),
//                        decimalFormat.format((j * span + span - 1).toLong()))
//                val realIndex = todayIndexPrefix + "." + indexSuffix
//                val indexAliasList = ArrayList<String>(span)
//                for (k in 0 until span) {
//                    val alias = todayIndexPrefix + "." + decimalFormat.format((j * span + k).toLong())
//                    indexAliasList.add(alias)
//                }
//                val indexRequest = IndexRequest(realIndex)
//                        .opType(DocWriteRequest.OpType.INDEX)
//                indexRequest.type("logs")
//                indexRequest.source(emptyMap<K, V>())
//                indexRequest.version(Versions.MATCH_ANY)
//                indexRequest.id(UUID.randomUUID().toString())
//                indexRequest.timeout(TimeValue(5, TimeUnit.MINUTES))
//
//                /*
//                actions : list
//                    add : map
//                        index : realIndex
//                        alias : aliasIndex
//                */
//                val map = HashMap<String, List<Map<String, Map<String, String>>>>(1)
//                val list = LinkedList<Map<String, Map<String, String>>>()
//                map["actions"] = list
//
//                indexAliasList.forEach { alias ->
//                    val param = HashMap<String, String>(2)
//                    param["index"] = realIndex
//                    param["alias"] = alias
//                    val add = HashMap<String, Map<String, String>>(1)
//                    add["add"] = param
//                    list.add(add)
//                }
//
//                indexRequest.timeout("30s")
//
//                logger.info("生成索引 ：" + realIndex + ", 别名：" + JSONObject.toJSONString(indexAliasList))
//
//                try {
//                    val indexResponse = sysLogEsClient.getHighLevelClient().index(indexRequest)
//                    if (indexResponse.getResult() === DocWriteResponse.Result.CREATED) {
//                        logger.info("ES 索引步创建完成, index = " + indexResponse.getShardId().getIndex())
//                    } else {
//                        continue
//                    }
//                    val entity = StringEntity(JSONObject.toJSONString(map), ContentType.APPLICATION_JSON)
//                    val response = sysLogEsClient.getHighLevelClient().getLowLevelClient().performRequest("POST", "/_aliases", emptyMap<K, V>(), entity)
//                    if (response.getStatusLine().getStatusCode() === 200) {
//                        logger.info("ES 索引别名创建完成，aliases = " + JSONObject.toJSONString(indexAliasList))
//                    }
//                } catch (e: Exception) {
//                    if (!e.message.contains("timeout")) {
//                        logger.info(e.message, e)
//                    }
//                }
//
//            }

    }
}