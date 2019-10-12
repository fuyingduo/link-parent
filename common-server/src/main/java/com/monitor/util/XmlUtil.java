package com.monitor.util;

import com.google.common.base.Charsets;

import javax.xml.bind.*;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Optional;

/**
 * created by fuyd on 2019-06-18
 */
public class XmlUtil {

    /**
     * 映射java实体 实体类标注@XmlRootElement(name = "xxx")
     *
     * @param inputStream 输入流
     * @param cls         映射实体
     * @param <T>         实体类型
     * @return
     */
    public static <T> Optional<T> xmlTobean(InputStream inputStream, Class<T> cls) {
        if (null == inputStream || null == cls) {
            return Optional.empty();
        }
        try {
            return Optional.ofNullable(JAXB.unmarshal(inputStream, cls));
        } catch (DataBindingException ignored) {
        }
        return Optional.empty();
    }

    /**
     * 映射java实体 实体类标注@XmlRootElement(name = "xxx")
     *
     * @param xmlContent xml消息内容
     * @param cls        映射实体
     * @param <T>        实体类型
     * @return
     */
    public static <T> Optional<T> xmlTobean(String xmlContent, Class<T> cls) {

        if (null == xmlContent || "".equals(xmlContent) || null == cls) {
            return Optional.empty();
        }
        try {
            return Optional.ofNullable(JAXB.unmarshal(new StringReader(xmlContent), cls));
        } catch (DataBindingException ignored) {
        }
        return Optional.empty();
    }


    /**
     * javabean 转换xml文件
     *
     * @param t        java实体类
     * @param fragment 是否去掉xml头信息
     * @return
     */
    public static <T> Optional<String> beanToXml(T t, Boolean fragment) {
        if (null == t || null == fragment) {
            return Optional.empty();
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            JAXBContext context = JAXBContext.newInstance(t.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, fragment);
            marshaller.marshal(t, out);
            return Optional.of(new String(out.toByteArray(), Charsets.UTF_8));
        } catch (JAXBException e) {
            return Optional.empty();
        }
    }
}
