const merge = require('webpack-merge');
const common = require('./webpack.common.js');

const UglifyJsPlugin = require('uglifyjs-webpack-plugin')

module.exports = merge(common, {
   devtool: false,
   plugins: [
      new UglifyJsPlugin({
         uglifyOptions: {
            compress: {
               warnings: false,
               conditionals: true,
               unused: true,
               comparisons: true,
               sequences: true,
               dead_code: true,
               evaluate: true,
               if_return: true,
               join_vars: true,
            },
            output: {
               comments: false
            }
         }
      })
   ]
});
