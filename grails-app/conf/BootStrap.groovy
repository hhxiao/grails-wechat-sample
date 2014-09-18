class BootStrap {
    def wechatService

    def init = { servletContext ->
        println wechatService
    }
    def destroy = {
    }
}
