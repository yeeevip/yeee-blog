import request from '@/utils/request'

export function recordBlogStatsData (event, blogId) {
  var params = new URLSearchParams();
  params.append("subject", "blog");
  params.append("subjectId", blogId);
  params.append("event", event);
  params.append("timestamp", new Date().getTime());
  return request({
    url: process.env.WEB_API + '/api/blog/stats/',
    method: 'post',
    params
  })
}
