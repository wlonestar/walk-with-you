import axios from 'axios'

const request = axios.create({
  baseURL: 'http://localhost:8088/',
  timeout: 6000
})

request.interceptors.request.use(config => {
  const tokenName = localStorage.getItem('tokenName')
  const tokenValue = localStorage.getItem('tokenValue')
  const header = {
    'content-type': 'application/json'
  }
  // eslint-disable-next-line @typescript-eslint/ban-ts-comment
  // @ts-ignore
  header[tokenName] = tokenValue
  if (tokenValue) {
    config.headers = header
  }
  return config
}, error => {
  return Promise.reject(error)
})

request.interceptors.response.use(res => {
  let data = res.data
  if (res.config.responseType === 'blob') {
    return data
  }
  if (typeof data === 'string') {
    data = data ? JSON.parse(data) : data
  }
  return data
}, error => {
  return Promise.reject(error)
})

export default request
