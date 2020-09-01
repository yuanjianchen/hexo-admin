/// <reference types="./types" />
declare class Vditor {
    /** 为 element 中的代码块添加复制按钮 */
    static codeRender: (element: HTMLElement, lang?: "en_US" | "ja_JP" | "ko_KR" | "zh_CN") => void;
    /** 对 graphviz 进行渲染 */
    static graphvizRender: (element: HTMLElement, cdn?: string) => void;
    /** 为 element 中的代码块进行高亮渲染 */
    static highlightRender: (hljsOption?: IHljs, element?: HTMLElement | Document, cdn?: string) => void;
    /** 对数学公式进行渲染 */
    static mathRender: (element: HTMLElement, options?: {
        cdn?: string;
        math?: IMath;
    }) => void;
    /** 转换 element 中 class 为 className 的元素为流程图/时序图/甘特图 */
    static mermaidRender: (element: HTMLElement, className?: string, cdn?: string) => void;
    /** 图表渲染 */
    static chartRender: (element?: HTMLElement | Document, cdn?: string) => void;
    /** 五线谱渲染 */
    static abcRender: (element?: HTMLElement | Document, cdn?: string) => void;
    /** 脑图渲染 */
    static mindmapRender: (element?: HTMLElement | Document, cdn?: string) => void;
    /** 大纲渲染 */
    static outlineRender: (contentElement: HTMLElement, targetElement: Element, vditor?: IVditor) => void;
    /** 为[特定链接](https://github.com/Vanessa219/vditor/issues/7)分别渲染为视频、音频、嵌入的 iframe */
    static mediaRender: (element: HTMLElement) => void;
    /** 对选中的文字进行阅读 */
    static speechRender: (element: HTMLElement, lang?: "en_US" | "ja_JP" | "ko_KR" | "zh_CN") => void;
    /** 对图片进行懒加载 */
    static lazyLoadImageRender: (element?: HTMLElement | Document) => boolean;
    /** Markdown 文本转换为 HTML，该方法需使用[异步编程](https://hacpai.com/article/1546828434083?r=Vaness) */
    static md2html: (mdText: string, options?: IPreviewOptions) => Promise<string>;
    /** 页面 Markdown 文章渲染 */
    static preview: (previewElement: HTMLDivElement, markdown: string, options?: IPreviewOptions) => Promise<void>;
    /** 设置代码主题 */
    static setCodeTheme: (codeTheme: string, cdn?: string) => void;
    /** 设置内容主题 */
    static setContentTheme: (contentTheme: string, path: string) => void;
}
export default Vditor;
