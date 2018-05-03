package org.intsmaze.core.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class HttpClientUtil {

	private static final Logger log = Logger.getLogger(HttpClientUtil.class);

	/**
	 * 
	 * @param url
	 * @param json
	 * @param character
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws Exception
	 */
	public static JSONObject sendHttpPostWithJson(String url, JSONObject json, String character) {

		CloseableHttpClient httpClient = null;
		HttpPost httpPost = null;
		String respJsonStr = null;
		try {
			httpClient = HttpClients.createDefault();
			httpPost = new HttpPost(url);

			StringEntity entity = new StringEntity(json.toString(), character);
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			httpPost.setEntity(entity);

			CloseableHttpResponse resp = httpClient.execute(httpPost);
			try {
				if (resp.getStatusLine().getStatusCode() == 200) {
					HttpEntity he = resp.getEntity();
					respJsonStr = EntityUtils.toString(he, "UTF-8");
					JSONObject respJson = JSON.parseObject(respJsonStr);
					return respJson;

				} else {
					log.info("访问的url为:" + url + " 返回的异常状态码为:" + resp.getStatusLine().getStatusCode());
					return null;
				}
			} catch (Exception e) {
				log.error(e);
			} finally {
				resp.close();
			}
		} catch (Exception e) {
			log.error(e);
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				log.error(e);
			}
		}
		return null;
	}

	/**
	 * 
	 * @param url
	 * @param parameter
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws Exception
	 */
	public static JSONObject sendHttpPut(String url, String parameter)
			throws ClientProtocolException, IOException, Exception {

		CloseableHttpClient httpClient = null;
		HttpPut put = null;
		String result = null;

		try {
			httpClient = HttpClients.createDefault();
			put = new HttpPut(url + parameter);
			CloseableHttpResponse response = httpClient.execute(put);
			try {
				if (response != null && response.getStatusLine() != null
						&& response.getStatusLine().getStatusCode() == 200) {
					HttpEntity httpEntity = response.getEntity();
					if (httpEntity != null) {
						result = EntityUtils.toString(httpEntity, "UTF-8");
						JSONObject respJson = JSON.parseObject(result);
						return respJson;
					}
				}
			} catch (Exception e) {
				log.error(e);
			} finally {
				response.close();
			}
		} catch (Exception e) {
			log.error(e);
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				log.error(e);
			}
		}
		return null;

	}

	/**
	 * 
	 * @param url
	 * @param parameter
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws Exception
	 */

	public static JSONObject sendHttpDelete(String url, String parameter)
			throws ClientProtocolException, IOException, Exception {
		CloseableHttpClient httpClient = null;
		HttpDelete httpDelete = null;
		String respJsonStr = null;

		try {
			httpClient = HttpClients.createDefault();
			httpDelete = new HttpDelete(url + parameter);
			CloseableHttpResponse resp = httpClient.execute(httpDelete);
			try {
				if (resp.getStatusLine().getStatusCode() == 200) {
					HttpEntity he = resp.getEntity();
					respJsonStr = EntityUtils.toString(he, "UTF-8");
					JSONObject respJson = JSON.parseObject(respJsonStr);
					return respJson;
				} else {
					throw new Exception(
							"访问的url为:" + url + parameter + " 返回的异常状态码为:" + resp.getStatusLine().getStatusCode());
				}
			} catch (Exception e) {
				log.error(e);
			} finally {
				resp.close();
			}
		} catch (Exception e) {
			log.error(e);
		} finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				log.error(e);
			}
		}
		return null;

	}

}
