const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');


module.exports = {
    // 入口文件
    entry: './App.js',

    // 输出配置
    output: {
        filename: 'bundle.js', // 输出文件名
        path: path.resolve(__dirname, 'dist'), // 输出路径
    },

    // 加载器
    module: {
        rules: [
            {
                test: /\.css$/, // 正则表达式，匹配 .css 文件
                use: [
                    'style-loader', // 将 JS 字符串生成为 style 节点
                    'css-loader',   // 将 CSS 转化成 CommonJS 模块
                ],
            },
            {
                test: /\.(png|svg|jpg|gif)$/,
                use: [
                    'file-loader',  // 处理图片文件
                ],
            },
        ],
    },

    // 插件
    plugins: [
        new HtmlWebpackPlugin({
            title: 'My App',  // 应用的标题
            template: './Home.html',  // 源 HTML 文件的路径
        }),
    ],

    // 开发工具
    devtool: 'inline-source-map',
    mode: 'development',
    // 开发服务器配置
    devServer: {
        contentBase: './dist',
    },
};
