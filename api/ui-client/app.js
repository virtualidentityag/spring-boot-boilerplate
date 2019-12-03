const express = require('express');
const app = express();
const swaggerUi = require('swagger-ui-express');
const YAML = require('yamljs');
const swaggerDocument = YAML.load('./../api.yaml');

var PORT = 9999;
var ROOT_PATH = '/';

app.use(ROOT_PATH, swaggerUi.serve, swaggerUi.setup(swaggerDocument));
app.listen(PORT, function () {
  console.log('REST API Docs available under http://localhost:' + PORT
      + ROOT_PATH)
});