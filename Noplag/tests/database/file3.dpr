{apptype console}
var 
  N : integer;
  i, num : integer;

  function gcd(a, b : integer) : integer;
  begin
    while (a > 0) and (b > 0) do
    begin
      if a > b then
        a := a mod b
      else 
        b := b mod a;
    end;
    result := a + b; // now : (a = 0) or (b = 0)
  end;

begin
  read(N);
  for i := 1 to N do
    if gcd(i, N) = 1 then
      inc(num);
  writeln(num);
end.