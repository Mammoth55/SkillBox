SELECT * FROM animals where type='травоядное';
SELECT * FROM animals where (type='травоядное' OR type='плотоядное') AND height='маленькое';
SELECT * FROM animals where (type='всеядное') AND NOT height='высокое';
