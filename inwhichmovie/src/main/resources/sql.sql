select idimdb, count(*)
from filme
group by idimdb
having count(*) > 1;

select * from filme
where idimdb = '0185431';