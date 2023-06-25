module.exports = {
    lintOnSave: false,
    chainWebpack: config => {
        // 发布模式的入口文件
        config.when(process.env.NODE_ENV === 'production', config => {
            config.entry('app').clear().add('./src/main-prod.js')
        })

        // 开发模式的入口文件
        config.when(process.env.NODE_ENV === 'development', config => {
            config.entry('app').clear().add('./src/main-dev.js')
        })
    }
}