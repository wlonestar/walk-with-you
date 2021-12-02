import request from '@/util/request.ts'
import { AxiosResponse } from 'axios'

// 获取所有用户
export function getAllUser () : Promise<AxiosResponse> {
  return request({
    url: '/user/all',
    method: 'get'
  })
}

// 根据 id 获取用户
export function getUserById (id: number) : Promise<AxiosResponse> {
  return request({
    url: '/user/',
    method: 'get',
    params: {
      id: id
    }
  })
}

// 根据用户名获取用户
export function getUserByUsername (username: string) : Promise<AxiosResponse> {
  return request({
    url: '/user/name',
    method: 'get',
    params: {
      username: username
    }
  })
}

// 获取所有用户和角色
export function getAllUserAndRole () : Promise<AxiosResponse> {
  return request({
    url: '/user/role',
    method: 'get'
  })
}

// 添加用户
export function addUser (user: unknown) : Promise<AxiosResponse> {
  return request({
    url: '/user/',
    method: 'post',
    data: user
  })
}

// 根据 id 更新用户
export function updateUser (id: number, user: unknown) : Promise<AxiosResponse> {
  return request({
    url: '/user/',
    method: 'put',
    params: id,
    data: user
  })
}

// 根据用户名更新用户
export function updateUserByUsername (username: string, user: unknown) : Promise<AxiosResponse> {
  return request({
    url: '/user/name',
    method: 'put',
    params: {
      username: username
    },
    data: user
  })
}

// 删除用户
export function deleteUser (id: number) : Promise<AxiosResponse> {
  return request({
    url: `/user/${id}`,
    method: 'delete'
  })
}
