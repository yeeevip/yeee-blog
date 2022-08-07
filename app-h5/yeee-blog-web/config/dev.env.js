'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',

  VUE_YEEE_WEB: '"http://localhost:8801"',
  PICTURE_API: '"http://localhost:8801"',
	WEB_API: '"http://localhost:8801"',
  SEARCH_API: '"http://localhost:8801"',

})
