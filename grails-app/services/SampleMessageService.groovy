import org.grails.plugin.wechat.annotation.MessageHandler
import org.grails.plugin.wechat.message.*
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
        wechatResponseService.responseText(message, "收到：" + message.content)
    }

    ResponseMessage onImage(ImageMessage message) {
        wechatResponseService.responseNews(message, new Article([
            title: "标题一", description: '描述一', picUrl: message.picUrl
        ]), new Article(
            title: "标题二", description: '描述二', picUrl: message.picUrl
        ))
    }

    @MessageHandler(MsgType.location)
    ResponseMessage onLocationReceived(LocationMessage message) {
        wechatResponseService.responseText(message, "收到位置消息: ${message.label}")
    }

    @MessageHandler(value=MsgType.event, events=[EventType.SCAN])
    ResponseMessage onScanned(EventMessage message) {
        wechatResponseService.responseText(message, "扫描了: ${message.eventKey}")
    }

    @MessageHandler(value=MsgType.event, events=[EventType.CLICK])
    ResponseMessage onMenuClicked(EventMessage message) {
        wechatResponseService.responseText(message, "点击了：${message.eventKey}")
    }

    @MessageHandler(value=MsgType.event, events=[EventType.VIEW])
    ResponseMessage onItemViewed(EventMessage message) {
        wechatResponseService.responseText(message, "查看了：${message.eventKey}")
    }

    @MessageHandler(value=MsgType.event, events=[EventType.LOCATION])
    ResponseMessage onLocationEvent(EventMessage message) {
        wechatResponseService.responseText(message, "收到位置事件: ${message.latitude}:${message.longitude}:${message.precision}")
    }

    ResponseMessage onMessage(Message message) {
        // handl the message ...
        System.out.println("Got a ${message.msgType} message")
        return null // return null to use response message from other callback
    }
}
