import request from '@/utils/request'
import commonUtil from "@/utils/commonUtil";

export function recordArticleStatsData (event, blogId) {
  var params = new URLSearchParams();
  params.append("subject", "article");
  params.append("subjectId", blogId);
  params.append("event", event);
  params.append("timestamp", new Date().getTime());
  return request({
    url: commonUtil.FUNCTIONS.base_url() + '/api/blog/stats/',
    method: 'post',
    params
  })
}

export function recordIndexStatsData (event) {
  var params = new URLSearchParams();
  params.append("subject", "index");
  params.append("event", event);
  params.append("timestamp", new Date().getTime());
  return request({
    url: commonUtil.FUNCTIONS.base_url() + '/api/blog/stats/',
    method: 'post',
    params
  })
}
