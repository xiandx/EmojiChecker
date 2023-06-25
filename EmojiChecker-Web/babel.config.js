const prodPlugins = []
    // 让移除console这个功能只在发布阶段应用
if (process.env.NODE_ENV === 'production') {
    prodPlugins.push('transform-remove-console')
}
module.exports = {
    presets: [
        '@vue/cli-plugin-babel/preset'
    ],
    plugins: [
        [
            'component',
            {
                libraryName: 'element-ui',
                styleLibraryName: 'theme-chalk'
            }
        ],
        // 'transform-remove-console'
        // 发布产品时候的插件数组
        ...prodPlugins
    ]
}