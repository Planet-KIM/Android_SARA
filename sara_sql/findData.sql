select data.id,  company.name, product.name, data.spec, data.tanktype, data.version, data.des
from data, company, product
where company.id = data.companyid AND  product.id = data.productid