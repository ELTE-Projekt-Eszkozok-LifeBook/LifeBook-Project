
export async function get(url){
  return await fetch(url);
}

export async function modify(url, elem){
    return await fetch(url, {
      method: 'PUT',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(elem)
    })
}

export async function post(url, elem){
    return await fetch(url, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(elem)
    })
}

export async function remove(url){
    return await fetch(url, {
        method: 'DELETE',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        }
    })
}