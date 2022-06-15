select part_num as 'Part Number', part_name as 'Part Name', location_name as 'Location Name', stock
from inventory i
INNER JOIN parts p on p.part_num = i.part_num_fk
inner join locations l on l.location_num = i.location_num_fk;