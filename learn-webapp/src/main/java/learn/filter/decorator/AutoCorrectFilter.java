package learn.filter.decorator;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

import java.io.IOException;
import java.util.*;

/**
 * @author Code Grass
 * @version 1.0
 * @className AutoCorrectFilter
 * @description 验证decorator或者wrapper模式，
 * 该模式需和被修改类共用一个接口，然后继承wrapper类；
 * 例子中是将所有请求参数值中包含空格的值替换为非空格
 * @date 2022/5/1 23:08
 */
public class AutoCorrectFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        AutoCorrectHttpServletRequest autoCorrectHttpServletRequest = new AutoCorrectHttpServletRequest(httpServletRequest);

        doFilter(autoCorrectHttpServletRequest, servletResponse, filterChain);
    }

    public static class AutoCorrectHttpServletRequest extends HttpServletRequestWrapper {
        private HttpServletRequest httpServletRequest;

        public AutoCorrectHttpServletRequest(HttpServletRequest request) {
            super(request);
            this.httpServletRequest = request;
        }

        @Override
        public String getParameter(String name) {
            return autoCorrect(httpServletRequest.getParameter(name));
        }

        @Override
        public String[] getParameterValues(String name) {
            return autoCorrect(httpServletRequest.getParameterValues(name));
        }

        @Override
        public Map<String, String[]> getParameterMap() {
            final Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
            Map<String, String[]> newMap = new Map<String, String[]>() {

                @Override
                public int size() {
                    return parameterMap.size();
                }

                @Override
                public boolean isEmpty() {
                    return parameterMap.isEmpty();
                }

                @Override
                public boolean containsKey(Object key) {
                    return parameterMap.containsKey(key);
                }

                @Override
                public boolean containsValue(Object value) {
                    return parameterMap.containsValue(value);
                }

                @Override
                public String[] get(Object key) {
                    return AutoCorrectHttpServletRequest.this.autoCorrect(parameterMap.get(key));
                }

                @Override
                public String[] put(String key, String[] value) {
                    return parameterMap.put(key, value);
                }

                @Override
                public String[] remove(Object key) {
                    return parameterMap.remove(key);
                }

                @Override
                public void putAll(Map<? extends String, ? extends String[]> m) {
                    parameterMap.putAll(m);
                }

                @Override
                public void clear() {
                    parameterMap.clear();
                }

                @Override
                public Set<String> keySet() {
                    return parameterMap.keySet();
                }

                @Override
                public Collection<String[]> values() {
                    return autoCorrect(parameterMap.values());
                }

                private Collection<String[]> autoCorrect(Collection<String[]> values) {
                    List<String[]> collection = new ArrayList();
                    for (String[] value : values) {
                        collection.add(AutoCorrectHttpServletRequest.this.autoCorrect(value));
                    }
                    return collection;
                }

                @Override
                public Set<Entry<String, String[]>> entrySet() {
                    return AutoCorrectHttpServletRequest.this.autoCorrect(parameterMap.entrySet());
                }
            };
            return newMap;

        }

        private Set<Map.Entry<String, String[]>> autoCorrect(Set<Map.Entry<String, String[]>> entrySet) {
            Set<Map.Entry<String, String[]>> newSet = new HashSet<>();
            for (Map.Entry<String, String[]> stringEntry : entrySet) {
                Map.Entry<String, String[]> newEntry = new Map.Entry<String, String[]>() {
                    @Override
                    public String getKey() {
                        return stringEntry.getKey();
                    }

                    @Override
                    public String[] getValue() {
                        return AutoCorrectHttpServletRequest.this.autoCorrect(stringEntry.getValue());
                    }

                    @Override
                    public String[] setValue(String[] value) {
                        return stringEntry.setValue(value);
                    }
                };
                newSet.add(newEntry);
            }
            return newSet;
        }

        public String[] autoCorrect(String[] parameterValues) {
            List<String> arrayList = new ArrayList<>();
            for (String parameterValue : parameterValues) {
                arrayList.add(autoCorrect(parameterValue));
            }
            return arrayList.toArray(new String[0]);
        }

        public String autoCorrect(String parameter) {
            if (parameter == null) {
                return null;
            }

            if (parameter.contains(" ")) {
                return parameter.replace(" ", "");
            }
            return parameter;
        }

    }
}
