import org.grails.plugin.wechat.annotation.MessageHandler
import org.grails.plugin.wechat.message.*
import org.grails.plugin.wechat.util.MessageUtils

/**
 * Created by haihxiao on 2014/9/30.
 */
class SampleMessageService {
    def wechatResponseService

    @MessageHandler(value=MsgType.event, events=[EventType.subscribe, EventType.unsubscribe])
    ResponseMessage onSubscriptionChanged(EventMessage message) {
        wechatResponseService.responseText(message, (message.event == EventType.subscribe) ? '欢迎' : '再见')
    }

    ResponseMessage onText(TextMessage message) {
        MessageUtils.fromXml("""<xml>
<ToUserName><![CDATA[${message.fromUserName}]]></ToUserName>
<FromUserName><![CDATA[${message.toUserName}]]></FromUserName>
<CreateTime>12345678</CreateTime>
<MsgType><![CDATA[text]]></MsgType>
<Content><![CDATA[received: ${message.content}]]></Content>
</xml>""")
    }

    ResponseMessage onImage(ImageMessage message) {
        MessageUtils.fromXml("""<xml>
<ToUserName><![CDATA[${message.fromUserName}]]></ToUserName>
<FromUserName><![CDATA[${message.toUserName}]]></FromUserName>
<CreateTime>12345678</CreateTime>
<MsgType><![CDATA[image]]></MsgType>
<MediaId><![CDATA[${message.mediaId}]]></MediaId>
</xml>""")
    }

    @MessageHandler(value=MsgType.event, events=[EventType.CLICK, EventType.SCAN, EventType.LOCATION, EventType.VIEW])
    ResponseMessage onLocation(EventMessage message) {
        MessageUtils.fromXml("""<xml>
<ToUserName><![CDATA[${message.fromUserName}]]></ToUserName>
<FromUserName><![CDATA[${message.toUserName}]]></FromUserName>
<CreateTime>12345678</CreateTime>
<MsgType><![CDATA[text]]></MsgType>
<Content><![CDATA[你好: ${message.event}]]></Content>
</xml>""")
    }
}
