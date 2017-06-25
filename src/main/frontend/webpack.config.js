/**
 * Created by Luan Hajzeraj on 17.06.2017.
 */

var CopyWebpackPlugin = require('copy-webpack-plugin');

module.exports = {
        entry: [
            './index.js'
        ],
        devtool: 'source-map',
        output: {
            path: __dirname + '/../resources/public', //Verschiebe also für die Springs-auslieferung alles dorthin
                publicPath: '/',
                filename: 'bundle.js'
        },
    plugins: [ //Kopiere mir AUCH alle Assets und die Index.html an /ressources/public, eben für Spring-auslieferung
        new CopyWebpackPlugin([
            {from: 'assets/**/*'},
            {from: 'index.html'}
        ])
    ],
    module: {
            loaders: [{
                exclude: /node_modules/,
                    loader: 'babel-loader',
                    query: {
                        presets: ['react', 'es2015', 'stage-1']
                    }
            }]
        },
    resolve: {
            extensions: ['.js', '.jsx']
        }
};