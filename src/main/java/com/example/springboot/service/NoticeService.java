package com.example.springboot.service;

import cn.hutool.core.util.ObjectUtil;
import com.example.springboot.common.ResultCode;
import com.example.springboot.common.config.JwtTokenUtils;
import com.example.springboot.dao.NoticeDao;
import com.example.springboot.entity.Account;
import com.example.springboot.entity.Notice;
import com.example.springboot.exception.CustomException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {
    private static final Logger logger = LoggerFactory.getLogger(NoticeService.class);

    @Resource
    NoticeDao noticeDao;

    public List<Notice> getAll() {
        logger.info("获取所有用户信息");
        return noticeDao.getAll();
    }

    /**
     * 用户新增
     * @param notice
     * @return
     */
    public Notice noticeRegister(Notice notice) {
        noticeDao.insert(notice);
        return notice;
    }


    public void deleteById(Integer id) {
        logger.info("根据 ID 删除用户信息，ID：{}", id);
        noticeDao.deleteById(id);
    }

    public Notice noticeUpdate(Notice notice) {
        noticeDao.update(notice);
        return notice;
    }

    public PageInfo<Notice> findPage(Notice search, Integer pageNum, Integer pageSize) {
        Account notice = JwtTokenUtils.getCurrentNotice();
        if (ObjectUtil.isEmpty(notice)) {
            logger.error("用户未登录，分页查询失败");
            throw new CustomException(ResultCode.USER_NOT_LOGIN);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Notice> all = findByCondition(search);
        return PageInfo.of(all);
    }

    public List<Notice> findByCondition(Notice search) {
        logger.info("根据条件查询用户信息，条件：{}", search);
        return noticeDao.findBySearch(search);
    }
}