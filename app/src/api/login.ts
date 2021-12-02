import request from '@/util/request.ts'
import { AxiosResponse } from 'axios'

// 登录
export function login (user: unknown) : Promise<AxiosResponse> {
  return request({
    url: '/admin/login',
    method: 'post',
    data: user
  })
}

// 验证
export function check (username: string, token: string) : Promise<AxiosResponse> {
  return request({
    url: '/admin/check',
    method: 'get',
    params: {
      username: username,
      tokenValue: token
    }
  })
}

// 注销
export function logout (token: string) : Promise<AxiosResponse> {
  return request({
    url: '/admin/logout',
    method: 'post',
    params: {
      tokenValue: token
    }
  })
}

// 注册
export function register (user: unknown) : Promise<AxiosResponse> {
  return request({
    url: '/admin/register',
    method: 'post',
    data: user
  })
}
