import request from '@/util/request.ts'
import { AxiosResponse } from 'axios'

// 分页查询留言
export function getCommentByPage (page: number, size: number) : Promise<AxiosResponse> {
  return request({
    url: '/comment/page',
    method: 'get',
    params: {
      page: page,
      size: size
    }
  })
}

// 获取所有留言
export function getAllComment () : Promise<AxiosResponse> {
  return request({
    url: '/comment/all',
    method: 'get'
  })
}

// 根据用户 id 获取留言
export function getCommentsById (id: number) : Promise<AxiosResponse> {
  return request({
    url: '/comment/id',
    method: 'get',
    params: {
      authorId: id
    }
  })
}

// 根据用户名获取留言
export function getCommentsByName (name: string) : Promise<AxiosResponse> {
  return request({
    url: '/comment/name',
    method: 'get',
    params: {
      username: name
    }
  })
}

// 根据 id 获取留言
export function getCommentById (id: number) : Promise<AxiosResponse> {
  return request({
    url: '/comment/',
    method: 'get',
    params: {
      id: id
    }
  })
}

// 添加留言
export function addComment (comment: unknown) : Promise<AxiosResponse> {
  return request({
    url: '/comment/',
    method: 'post',
    data: comment
  })
}

// 删除留言
export function deleteComment (id: number) : Promise<AxiosResponse> {
  return request({
    url: `/comment/${id}`,
    method: 'delete'
  })
}
