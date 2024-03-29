import {useEffect, useState} from 'react';
import getInstance from '../api/instances';

const useSearch = (query: string, type: string = 'track') => {
  const [response, setResponse] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      if (query === '') {
        return {};
      }
      const instance = await getInstance();
      const resp = await instance?.get(`search?type=${type}&q=${query}`);
      const items = resp.data.tracks.items.filter((i: any) => i.type === type);
      setResponse(items);
    };

    fetchData().catch((e) => console.log(e));
  }, [query, type]);

  return response;
};

export default useSearch;
